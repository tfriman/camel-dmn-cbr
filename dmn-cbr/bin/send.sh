#! /usr/bin/env sh

if [[ $# -eq 0 ]]
then
  url=http://localhost:8888/ContentBasedRouting
else
  url=$(oc get routes $1 -o 'jsonpath={.spec.port.targetPort}://{.spec.host}/ContentBasedRouting')
fi

curl -X POST -H "content-type: application/json" -d '{"TotalPrice": 9, "LoyaltyLevel": 2}' $url
