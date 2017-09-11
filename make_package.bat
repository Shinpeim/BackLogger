@rem npmパッケージインストール
cmd /c npm install

@rem パッケージ用ディレクトリ用意
if not exist package (
  mkdir package
)
del package /Q
if not exist package\web (
  mkdir package\web
)
del package\web /Q

@rem ビルド
cd scala
cmd /c sbt fullOptJS
cd ..
cd web
cmd /c npm install
cmd /c npm run build
cd ..

@rem コピー
copy package.json package
copy main.js package
copy yarn.lock package
xcopy assets package\assets /E /I /Y
xcopy web\build package\web\build /E /I /Y
xcopy node_modules package\node_modules /E /I /Y

@rem パッケージ
cd package
cmd /c yarn install
cmd /c npm run package