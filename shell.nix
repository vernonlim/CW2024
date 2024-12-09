# on NixOS, ensures maven and java 21 are installed
{ pkgs ? import <nixpkgs> {} }:
  pkgs.mkShell {
    nativeBuildInputs = [
      pkgs.graphviz
      pkgs.maven
      (pkgs.zulu.override { enableJavaFX = true; })
      pkgs.ffmpeg
    ];
}
