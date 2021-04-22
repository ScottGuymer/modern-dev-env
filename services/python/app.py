from flask import Flask
import requests
from random import randint
import time
import os
import time
from flask import jsonify
from flask import Response

app = Flask(__name__)
CONTENT_TYPE_LATEST = str('text/plain; version=0.0.4; charset=utf-8')

@app.route('/<service>')
def service(service):
    url = "http://" + service
 
    http_header_carrier = {}

   
    response = {
      "message": "Wrapped by Python",
      "response": ""
    }
    
    try:
      r = requests.get(url, headers=http_header_carrier)
      response['response'] = r.text  
    except Exception as ex:
      response['response'] = "Unable to talk to service " + service
    
    return jsonify(response)

@app.route('/')
def root():
    sleepTimer = randint(0, 1000)
    requestTimer = randint(0, 4)

    try:
        if sleepTimer > 900:
            raise Exception("I cant wait that long!")
        time.sleep(sleepTimer / 1000)
        url = "https://httpbin.org/delay/{:d}".format(requestTimer)
        r = requests.get(url)

        response = {
          "message": "Hello from Python!",
          "sleep": sleepTimer,
          "requestTime": requestTimer
        }        
    except Exception as e:
      response = {
        "message": "Error from Python!",
        "sleep": sleepTimer,
        "requestTime": requestTimer
      }

    
    return jsonify(response)

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=80)    
