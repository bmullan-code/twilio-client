#!/bin/bash
curl twilio-client.cfapps.io/sendSMS -X POST -d '{ "cell":"6173351288", "msg":"hello"}' -H "Content-Type: application/json"

