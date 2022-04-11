package com.practica.ventasmoviles.model

import com.practica.ventasmoviles.data.datasource.database.model.Productos

class ObjectsProvider {

    companion object{
        val productos = listOf(
            Productos("11111","zapato", 500.1F,
                        600.01F, 550.1F, "zapatos",
                          "nike", "rojo", "inch", 3),
            Productos("11111","camisa", 420.1F,
                120.01F, 350.1F, "camisas",
                "nike", "azul", "inch", 3),
            Productos("11111","anillo", 420.1F,
                120.01F, 350.1F, "anillos",
                "nike", "verde", "inch", 3),
            Productos("11111","madeja", 420.1F,
                120.01F, 350.1F, "camisas",
                "EL gato", "azul", "inch", 3),
            Productos("11111","flores", 420.1F,
                120.01F, 350.1F, "anillos",
                "jalisco", "verde", "inch", 3)
        )
    }
}