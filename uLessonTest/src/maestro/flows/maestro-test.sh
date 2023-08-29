#!/usr/bin/env sh

assert_maestro_installed() {
  PROGRAM=maestro

  export PATH=$PATH:$HOME/.maestro/bin

  if ! [ -x "$(command -v $PROGRAM)" ]; then
    echo 'Error: maestro is not installed.'

    echo "Now installing maestro..."
    (curl -Ls "https://get.maestro.mobile.dev" | bash) || exit

    export PATH=$PATH:$HOME/.maestro/bin
  fi
}

run_tests() {
  assert_maestro_installed
  maestro test --format junit main.yaml
}

run_tests