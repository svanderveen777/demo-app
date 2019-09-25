#!/usr/bin/groovy

try {
    timeout(time: 20, unit: 'MINUTES') {
        def appName="demo-app"
        def project = //Enter project name here
        node("maven") {
            stage("Checkout") {
                //checkout project
            }
            stage("Build Release") {
                //build with maven and run tests
            }
        }
        node {
              stage("Build Image") {
                   //Build image using s2i
                   //Use the following to build with s2i

                    sh "oc apply -f openshift/demo-app-imagestream.yml -n ${project}"
                    sh "oc apply -f openshift/demo-app-buildconfig.yml -n ${project}"

                    //add code to build via s2i here

                    openshiftVerifyBuild bldCfg: "${appName}-s2i", namespace: project, waitTime: '20', waitUnit: 'min'
                  }
                  stage("Deploy") {
                    //Apply your service/route/deploymentconfig here
                  
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