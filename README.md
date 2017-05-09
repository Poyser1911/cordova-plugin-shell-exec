# cordova-plugin-shell-exec

Apache Cordova Plugin to Execute Root Commands Android in Smartphone's Operating System Shell.


This is a MODIFIED version of [https://github.com/petervojtek/cordova-plugin-shell-exec](https://github.com/petervojtek/cordova-plugin-shell-exec) to be complatible with all commands formats without using arrays. <br/> See `Useage` and `Examples` <br/>
`Note: All Commands Excutes in Root Mode at the moment, this can be modified later.`


### Supported Platforms

* Android

### Installation

`$ cordova plugin add https://github.com/Poyser1911/cordova-plugin-shell-exec.git`

### Usage

```
window.ShellExec.exec(cmd, callback);
```

`cmd` : String.

callback : function will return a object with two keys - `exitStatus` and `output`.

Examples: `"Quotes Around Path are optional"`
```
var cmd = 'ls';
cmd = 'ls /data';
cmd = 'ls "/data/system\ prefs""; //escaped Whitespace

cmd = 'ls && ls /data && cp "/data/data/com.example.app" "/data/data/com.example.app2" && rm -r -f  "/data/data/com.example.app2"'; //Using &&
```

```
window.ShellExec.exec(cmd, (res) =>{
  alert('exit status: ' + res.exitStatus)
  alert('cmd output: ' + res.output)
})
```
### Alerts Result Preview
![alt text](http://poyser.pw/git/images/exitStatus.png "exitStatus")



![alt text](http://poyser.pw/git/images/output.png "output")

### Alerts Result IFError Preview
![alt text](http://poyser.pw/git/images/error.png "output")



```

