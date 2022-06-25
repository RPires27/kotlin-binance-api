package com.istec.projetoistecwebservice.cliente

import com.binance.connector.client.impl.SpotClientImpl
import com.istec.projetoistecwebservice.keys.Keys



class Cliente {
    val keys = Keys()
    val cliente = SpotClientImpl(keys.api_key,keys.secret_api_key,keys.binannce_api)
    val arroz = LinkedHashMap<String,Any>()






}