package com.academia.backend.clase.encapsulada;


public class UserMain {
	
	
	public static void main(String[] args) {
		
		//Creando un nuevo usuario
		User user1 = User.createNewUser("dani", "dani@gmail.com", "1a2s3d4f");

		//Creando usuarios con email y username repetidos (son hashsets) para verificar que no lo permite
		//Los sets son útiles en este caso pues no permiten repetidos
		User user2 = User.createNewUser("dani", "dani1@gmail.com", "1a2s3d4f");
		User user3 = User.createNewUser("danilo", "dani@gmail.com", "1a2s3d4f");
		
		//Creando usuario con una contraseña que no cumple los requerimntos
		User user4 = User.createNewUser("alejandra", "ale@gmail.com", "1a2s3d");
		
		
		//Creando un usuario con una contraseña correcta y luego intento cambiarla por una incorrecta
		User user5 = User.createNewUser("gustavo", "gus@gmail.com", "1a2s3d4f5g");
		
		//Intento cambiarla por una contraseña que ya ha sido usada
		user5.changePassword("1a2s3d4f5g");
		
		//Intento cambiarla por una contraseña demasiado corta
		user5.changePassword("1a2s3d");
		
		//Intento cambiar el email de user5 por uno ya en uso
		user5.changeEmail("dani@gmail.com");
		
		//Intento cambiar el username de user5 por uno ya en uso
		user5.changeUsername("dani");
		
		//Hago login con credenciales correctas
		user1.logIn("dani", "1a2s3d4f");
		
	}
}
