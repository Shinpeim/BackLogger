const {app, BrowserWindow, shell, Menu} = require('electron')
const path = require('path')
const url = require('url')

let win

function createWindow () {
  // Create the browser window.
  win = new BrowserWindow({width: 800, height: 600})

  // and load the index.html of the app.
  win.loadURL(url.format({
    pathname: path.join(__dirname, "web", "build", 'index.html'),
    protocol: 'file:',
    slashes: true
  }))

  win.webContents.on('new-window', (event, url) => {
    event.preventDefault();
    shell.openExternal(url);
  })

  win.on('closed', () => {
    win = null
  })
}

app.on('ready', function(){
    const template = [
        {
            submenu: [
                { label: "Quit", accelerator: "CmdOrCtrl+Q", role: "quit" },
                { label: "Cut", accelerator: "CmdOrCtrl+X", role: "cut" },
                { label: "Copy", accelerator: "CmdOrCtrl+C", role: "copy" },
                { label: "Paste", accelerator: "CmdOrCtrl+V", role: "paste" },
                { label: "SelectAll", accelerator: "CmdOrCtrl+A", role: "selectall" }
            ]
        }
    ];
    Menu.setApplicationMenu(Menu.buildFromTemplate(template));

    createWindow();
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
});

app.on('activate', () => {
  if (win === null) {
    createWindow()
  }
});


