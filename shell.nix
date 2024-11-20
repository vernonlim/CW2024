
{ pkgs ? import <nixpkgs> {} }:
  pkgs.mkShell {
    nativeBuildInputs = [
      pkgs.maven
      (pkgs.zulu.override { enableJavaFX = true; })
    ];
}
