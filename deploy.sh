# deploy app to prod server
set -e -o pipefail

./gradlew clean assemble deploy
scp ./deploy/tomcat/webapps/app/WEB-INF/lib/app-* s42@skywind.xyz:/tmp/
scp ./deploy/tomcat/webapps/app-core/WEB-INF/lib/app-* s42@skywind.xyz:/tmp/

ssh s42@skywind.xyz '/bin/bash ~/services/balance/deploy.sh'

