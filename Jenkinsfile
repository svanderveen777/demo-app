#!/usr/bin/groovy

          try {
             timeout(time: 20, unit: 'MINUTES') {
                def appName="demo-app"
                def project = "springboot-test"

                node("maven") {
                  stage("Checkout") {
                   //checkout ([$class: 'GitSCM', branches: [[name: "refs/heads/openshift"]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'LocalBranch', localBranch: openshift]], submoduleCfg: [], userRemoteConfigs: scm.userRemoteConfigs])
                    checkout scm
                  }
                  stage("Build Release") {
                    sh "mvn clean -B -e -U install -P openshift"
                  }
                }
                node {
                  stage("Build Image") {
                    checkout scm
                    sh "oc apply -f openshift/demo-app-imagestream.yml -n ${project}"
                    sh "oc apply -f openshift/demo-app-buildconfig.yml -n ${project}"
                    sh "oc start-build ${appName}-s2i --from-dir=. --follow -n ${project}"
                    openshiftVerifyBuild bldCfg: "${appName}-s2i", namespace: project, waitTime: '20', waitUnit: 'min'
                  }
                  stage("Deploy") {
                    sh "oc apply -f openshift/demo-app-service.yml -n ${project}"
                    sh "oc apply -f openshift/demo-app-route.yml -n ${project}"
                    sh "oc apply -f openshift/demo-app-deploymentconfig.yml -n ${project}"
                    openshiftDeploy deploymentConfig: appName, namespace: project
                  }
                }
             }
          } catch (err) {
             echo "in catch block"
             echo "Caught: ${err}"
             currentBuild.result = 'FAILURE'
             throw err
          }