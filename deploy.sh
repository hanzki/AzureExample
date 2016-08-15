#!/bin/bash

cp -f ./target/*.war ./circleciansible/webapps/
cd ./circleciansible
git add .
git commit -m "CircleCI build $CIRCLE_BUILD_NUM"
git push -f origin master
