# Bitcoin Wallet Reader

Given a xPub address display a scrollable list of transactions retrieved from Blockchain API

Example:
https://blockchain.info/multiaddr?active=xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ

![screenshot](./captures/bitcoin_wallet_capture.png?raw=true)

* MVP architecture
* Dagger 2 as injector
* Retrofit and OkHttp for managing connections
* Shared preferences as cached memory

## TO DO's
* Add xPub by scanning QR
* Add xPub manually by input text
* Finish transaction details
* Show QR from wallet addresses
* Room as persistent memory
* Instrumentation tests
* UI tests

## Maybe
* Move viewModel to Presenter and test interactions

