#!/bin/sh

set -xe

# パッケージ用ディレクトリ用意
mkdir -p package;
rm -rf package/*;
mkdir -p package/web/;

# ビルド
cd scala;
sbt fullOptJS;
cd ../;
cd web;
npm run build;
cd ../;

# コピー
cp package.json package/;
cp main.js package/;
cp yarn.lock package/;
cp -rp assets package/assets;
cp -rp web/build package/web/build;
cp -rp node_modules package/node_modules;

# package
cd package;
yarn install;
npm run package;
