package com.istec.projetoistecwebservice.controller

import com.istec.projetoistecwebservice.cliente.Cliente
import com.istec.projetoistecwebservice.cliente.ClienteRepo
import org.json.JSONArray
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
class RestController {

    val clientesrepo = ClienteRepo()


    @GetMapping("/coininfo")
    fun sayHellho():Any{

        return clientesrepo.verinfocoin()

    }

    @GetMapping("/deposit/history")
    fun sayadeus():Any{

        return clientesrepo.verhistoricodepositos()
    }

    @GetMapping("/withdraw/history")
    fun historicolevantamento():Any{

        return clientesrepo.historicolevantamentos()
    }

    @PostMapping("/testarordem/{par}/{venda_compra}/{quantidade}/{tipo}")
    fun testarordem(@PathVariable par:String, @PathVariable venda_compra:String, @PathVariable quantidade : String,@PathVariable tipo:String):Any {
        return clientesrepo.testarordem(par, venda_compra,tipo, quantidade)

    }

    @GetMapping("/depositaddress/{coin}")
    @ResponseBody
    fun depositaddress(@PathVariable coin :String):Any {

        return clientesrepo.getdepositaddress(coin)
    }



    @PostMapping("/getlistenkey")
    fun getlistenkey():Any{
        return clientesrepo.getlistenkey()
    }

    @PutMapping("/pinglistenkey")
    fun pinglistenkey():Any{
        return clientesrepo.pinglistenkey()
    }


    @DeleteMapping("/dellistenkey")
    fun dellistenkey():Any{
        return clientesrepo.dellistenkey()
    }

    @GetMapping("/price/{par}")
    fun getprice(@PathVariable par : String):Any{

        return clientesrepo.getsymbolprice(par)
    }



}