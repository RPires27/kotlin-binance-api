package com.istec.projetoistecwebservice.cliente

import io.github.g0dkar.qrcode.QRCode
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import kotlin.collections.LinkedHashMap



@Repository
class ClienteRepo {

    val cliente = Cliente()
    val formatodata = SimpleDateFormat("dd MMMM yyyy, HH:mm")



    fun verinfocoin():Any{

        val json = cliente.cliente.createWallet().coinInfo(cliente.arroz)
        val map = JSONArray(json)
        val lunc = map.getJSONObject(131).get("coin")
        val lunc_value = map.getJSONObject(131).get("free")
        val btc = map.getJSONObject(343).get("coin")
        val btc_value = map.getJSONObject(343).get("free")
        val eth = map.getJSONObject(232).get("coin")
        val eth_value = map.getJSONObject(232).get("free")
        val ada = map.getJSONObject(70).get("coin")
        val ada_value = map.getJSONObject(70).get("free")
        val sol = map.getJSONObject(222).get("coin")
        val sol_value = map.getJSONObject(222).get("free")
        val usdt = map.getJSONObject(509).get("coin")
        val usdt_value = map.getJSONObject(509).get("free")
        val bnb = map.getJSONObject(228).get("coin")
        val bnb_value = map.getJSONObject(228).get("free")
        val result_lunc = "${lunc} = ${lunc_value}"
        val result_btc = "${btc} = ${btc_value}"
        val result_eth = "${eth} = ${eth_value}"
        val result_ada = "${ada} = ${ada_value}"
        val result_sol = "${sol} = ${sol_value}"
        val result_usdt = "${usdt} = ${usdt_value}"
        val result_bnb = "${bnb} = ${bnb_value}"
        return "${result_btc}<br>${result_eth}<br>${result_bnb}<br>${result_sol}<br>${result_ada}<br>${result_lunc}<br>${result_usdt}"
        //return json
    }
    fun verhistoricodepositos():Any{

        val json = cliente.cliente.createWallet().depositHistory(cliente.arroz)
        val map = JSONArray(json)
        val montante = map.getJSONObject(0).get("amount")
        val moeda = map.getJSONObject(0).get("coin")
        val network = map.getJSONObject(0).get("network")
        val endereco = map.getJSONObject(0).get("address")
        val tempo = map.getJSONObject(0).get("insertTime")
        val tempo_formatado : String = formatodata.format(tempo)
        return "Recebido ${montante} de ${moeda} pelo endereço ${endereco} pela rede ${network} | ${tempo_formatado}"
    }

    fun historicolevantamentos():Any{

        val json = cliente.cliente.createWallet().withdrawHistory(cliente.arroz)

        val map = JSONArray(json)
        val montante = map.getJSONObject(0).get("amount")
        val moeda = map.getJSONObject(0).get("coin")
        val network = map.getJSONObject(0).get("network")
        val tempo = map.getJSONObject(0).get("applyTime")
        val endereco = map.getJSONObject(0).get("address")
        return "Enviados ${montante} de ${moeda} para o endereço ${endereco} pela rede ${network} | ${tempo}"
    }



    fun getdepositaddress(coin : String):Any{

        val parametros = LinkedHashMap<String,Any>()
        parametros.put("coin", coin)

        val depositaddress = cliente.cliente.createWallet().depositAddress(parametros)
        val json = JSONObject(depositaddress)
        val endereco : String = json.get("address").toString()
        val imageout = ByteArrayOutputStream()
        QRCode(endereco).render(cellSize = 10, margin = 10).writeImage(imageout)
        val imageBytes = imageout.toByteArray()
        val resource = ByteArrayResource(imageBytes)

        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(resource)
    }

    fun testarordem(par : String, venda_compra: String , tipo:String,quantidade:String):Any{


        val parametros = LinkedHashMap<String,Any>()
        parametros.put("symbol",par)
        parametros.put("side", venda_compra)
        parametros.put("type", tipo)
        parametros.put("quantity",quantidade)



        val req = cliente.cliente.createTrade().testNewOrder(parametros)


        return "Ordem teste: ${venda_compra} de ${quantidade} de ${par} no tipo de mercado ${tipo}"
    }






    fun getlistenkey():Any {

        val a = cliente.cliente.createUserData().createListenKey()
        val json = JSONObject(a).get("listenKey")
        return json
    }

    fun dellistenkey():Any{
        val parametros = LinkedHashMap<String,Any>()
        parametros.put("listenKey", getlistenkey())
        val req = cliente.cliente.createUserData().closeListenKey(parametros)
        return req
    }

    fun pinglistenkey():Any{
        val parametros = LinkedHashMap<String, Any>()
        parametros.put("listenKey", getlistenkey())
        val req = cliente.cliente.createUserData().extendListenKey(parametros)
        return req
    }
    fun getsymbolprice(par: String):Any{

        val parametros = LinkedHashMap<String,Any>()
        parametros.put("symbol", par)
        val req = cliente.cliente.createMarket().tickerSymbol(parametros)
        val json = JSONObject(req).get("price")
        return "Preço = ${json}$"

    }






}