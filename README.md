# SimonDice

# Resumen del juego

Es un juego que consiste en un simon dice, el cual dispone de 4 colores, que se encienden de manera aleatoria y tienes que acertar la secuencia, en este caso el juego esta configurado mediante un Vista vista modelo, de tal manera que todo el codigo, esta en una clase con sus respectivas funciones y se llaman a la clase principal


![image](https://user-images.githubusercontent.com/91197896/207866105-d5513134-964e-4559-93a9-2d9c70b086b7.png)



# Codigo

**CODIGO VISTA VISTA MODELO**


* Val declaradas

  -Tendriamos por un lado, las val declaradas, que serian dos secuencias mutablelist, que serian las secuencias a  usar en las diferentes funciones, y y un gamestate que seria un boolean para mostrar el estado del juego.
  
  
 * Funciones
 
   - Por otro lado estarian las funciones del modelo que serian las siguientes
   
   1. Funcion de iniciar juego, funcion que tiene el boolean de manera false ya que hasta que se inicie dandole al boton de iniciar no hara nada lo cual iniciaria la primera secuencia.
   
   ![image](https://user-images.githubusercontent.com/91197896/226481887-3ed68412-93ec-4776-8841-a240ae1f8c62.png)

   
   2. Funcion addToSec, una vez iniciado el juego llamamos a esta funcion que va a inciar la secuencia, cuando pulsemos el boton.
   
   ![image](https://user-images.githubusercontent.com/91197896/226481932-7e85a5b5-0390-4744-a939-7b85aa1d5743.png)
   
   
   3. Funcion cehkSec, funcion que va a comprobar la secuencia anterior a traves del boton check y acto seguido inciara otra secuencia si se cumple el patron correspondiente.
   
   
   ![image](https://user-images.githubusercontent.com/91197896/226482133-60d7b158-979e-428e-9ad4-1d09f5c012d5.png)


   4.  Funcion reset reseteara la secuencia una vez fallemos y volvera al principio.
   
   ![image](https://user-images.githubusercontent.com/91197896/226482432-0d6eaf65-8373-437b-a06e-439698405275.png)
   
   5. Funcion AddUssec, que va hacer que que la secuencia parpade y cuando el patron se realize ira parpadenado cada color.
   
   ![image](https://user-images.githubusercontent.com/91197896/226483034-77714c5a-4b77-4b27-9e93-8e6045d0388b.png)
   
   6. funcion showSec que va mostrar la secuencia
   
   ![image](https://user-images.githubusercontent.com/91197896/226483122-fb2da3fc-dc5f-476f-810e-c7d0fc80baa7.png)


   

   
  









