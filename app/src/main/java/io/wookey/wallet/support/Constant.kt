package io.wookey.wallet.support

import io.wookey.wallet.data.entity.Coin
import io.wookey.wallet.data.entity.Node

const val WALLET_RECOVERY = 1
const val WALLET_CREATE = 0

const val TRANSFER_ALL = 0
const val TRANSFER_IN = 1
const val TRANSFER_OUT = 2

const val SELECT_ADDRESS = 1

const val REQUEST_SCAN_ADDRESS = 100
const val REQUEST_SELECT_COIN = 101
const val REQUEST_SELECT_ADDRESS = 102
const val REQUEST_SELECT_NODE = 103
const val REQUEST_SELECT_SUB_ADDRESS = 104

const val REQUEST_CODE_PERMISSION_CAMERA = 501

const val ZH_CN = "zh-CN"
const val EN = "en"

val coinList = listOf(
        Coin("EVO", "Coinevo")
)

val nodeArray = arrayOf(
        Node().apply {
            symbol = "EVO"
            url = "node.coinevo.tech:18089"
            isSelected = true
        },
        Node().apply {
            symbol = "EVO"
            url = "opennode.coinevo.tech:18089"
            isSelected = false
        },
        Node().apply {
            symbol = "EVO"
            url = "node1.coinevo.tech:18089"
            isSelected = false
        }
)
