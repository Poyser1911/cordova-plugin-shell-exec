function ShellExec() {
  this.exec = function(cmd, callback) {
    return cordova.exec(callback,rootMode, function(err) {
      callback({exitStatus: 100, output: err});
    }, "ShellExec", "exec", [cmd,rootMode]);

  };
}

window.ShellExec = new ShellExec()
