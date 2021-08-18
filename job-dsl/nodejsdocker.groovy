job('NodeJS Docker example') {
    scm {
        git('https://github.com/eraaaza/docker-demo.git', 'Master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('eraaaza')
            node / gitConfigEmail('bperaza415@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('eraaaza/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
