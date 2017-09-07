Adapted from https://github.com/egirault/googleplay-api

# Fixes

* Fixed token parsing bug.
* Stripped to bare download scripts.
* Updated device UA.
* More error statuses.
* Fixed login issues of the original repo.

# Install

In the project folder do:

```
sudo apt-get -y install python-pip
pip install requests protobuf
npm install # apt-get install npm
```

Configure config.py with your credentials.
Note that these are written to the token-dispenser automatically.

```
import sys
SEPARATOR = ";"
LANG = "en_US"
ANDROID_ID = "ANDROID_ID"
GOOGLE_LOGIN = "GOOGLE_EMAIL"
GOOGLE_PASSWORD = "GOOGLE_PASS"
```

Then simply add the project folder to the path and then execute like:

```
download com.facebook.katana /tmp/com.facebook.katana.apk
```

or simply: 

```
download com.facebook.katana
```