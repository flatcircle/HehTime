# PreferencesHelper

Functions to help with doing basic time-related things. Named after the [Egyptian God Heh](https://en.wikipedia.org/wiki/Heh_(god)).

[![CircleCI](https://circleci.com/gh/flatcircle/HehTime.svg?style=svg)](https://circleci.com/gh/flatcircle/HehTime) [![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)


Installation
--------

```groovy
implementation 'io.flatcircle:hehtime:{version}'
```

Usage
-----


| Function  | Description | Example |
| ------------- | ------------- | ------------- |
| set(context, key, value) | Saves a given value to sharedPreferences, where [value] is any primitive, or a custom class that can be serialized by Moshi into Json | [Example](https://github.com/flatcircle/PreferencesHelper/blob/master/app/src/main/java/io/flatcircle/preferencehelperexample/MainActivity.kt#L24)  |


Icon obtained from [ShareIcon.net](https://www.shareicon.net/mythology-egyptian-god-heh-hieroglyph-744495)