package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import controller.Controlador;

/**
 * Clase que simula un Menu interactivo. Contiene un Controlador
 * @version 11.0.13.8
 * @autor: John Serrano Carrasco
 */
public class Menu {
    private Controlador controlador;

    public Menu(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Obtiene al Controlador
     * @return Controlador Si se obtiene al Controlador
     */
    public Controlador getControlador() {
        return controlador;
    }

    /**
     * Modifica el controlador del Menu interactivo
     * @param controlador (Controlador). Corresponde a un Controlador que maneja las funcionalidades del Menu
     */
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Permite ejecutar las distintas funcionalidades del menu
     */
    public void ejecutarMenu() {
        Scanner input = new Scanner(System.in);
        boolean salirMenu = false;
        int eleccion;
        int eleccionShare;
        String username;
        String password;
        Controlador controlador = getControlador();
        while (!salirMenu) {
            if (controlador.estaConectado() == false) {
                System.out.println("### Bienvenido al editor colaborativo " + controlador.getEditor().getName() + " ###");
                System.out.println("Escoja la opcion que desea realizar: ");
                System.out.println("1. Loguearse");
                System.out.println("2. Registrarse");
                System.out.println("3. Ver documentos creados en el Editor");
                System.out.println("4. Salir");
                try {
                    System.out.println("Introduzca su eleccion: ");
                    eleccion = input.nextInt();
                    switch (eleccion) {

                        case 1: // LOGIN
                            System.out.println("Su opcion fue la numero 1: Loguearse");
                            System.out.println("Ingrese el nombre de usuario:");
                            input.nextLine();
                            username = input.nextLine();
                            System.out.println("Ingrese la contrasena para el usuario " + username);
                            password = input.nextLine();
                            controlador.login(username, password);
                            break;

                        case 2: // REGISTER
                            System.out.println("Su opcion fue la numero 2: Registrarse");
                            System.out.println("Ingrese el nombre de usuario: ");
                            input.nextLine();
                            username = input.nextLine();
                            System.out.println("Ingrese una contrasena: ");
                            password = input.nextLine();
                            controlador.register(username, password);
                            break;

                        case 3: // VISUALIZE
                            System.out.println("Su opcion fue la numero 3: Ver documentos creados en el Editor");
                            controlador.visualize();
                            break;

                        case 4: // CERRAR PROGRAMA
                            System.out.println("Gracias por utilizar " + controlador.getEditor().getName());
                            salirMenu = true;
                            input.close();
                            break;
                        default:
                            System.out.println("Seleccione nuevamente una de las opciones anteriores");
                            break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("El menu solo admite como entrada numeros y alguna de las opciones anteriores");
                    input.next();
                }
            } else {
                System.out.println("### Editor " + controlador.getEditor().getName() + " ###");
                System.out.println("## Logueado como: " + controlador.getEditor().getActivo().getUsername() + " ##");
                System.out.println("Escoja una accion a realizar:");
                System.out.println("1. Crear nuevo documento");
                System.out.println("2. Compartir documento");
                System.out.println("3. Agregar contenido a un documento");
                System.out.println("4. Restaurar version de un documento");
                System.out.println("5. Revocar acceso a un documento");
                System.out.println("6. Buscar un texto en los documentos");
                System.out.println("7. Visualizar documentos");
                System.out.println("8. Eliminar caracteres de un documento");
                System.out.println("9. Buscar y reemplazar un texto de un documento");
                System.out.println("10. Cerrar sesion");
                System.out.println("11. Cerrar el editor");
                try {
                    System.out.println("Ingrese una de las opciones anteriores: ");
                    eleccion = input.nextInt();
                    switch (eleccion) {

                        case 1: // CREATE
                            System.out.println("Su opcion fue la numero 1: Crear nuevo documento");
                            System.out.println("Ingrese el nombre del documento: ");
                            input.nextLine();
                            String nameDoc = input.nextLine();
                            System.out.println("Ingrese el contenido del documento: ");
                            String contentDoc = input.nextLine();
                            controlador.create(nameDoc, contentDoc);
                            break;

                        case 2: // SHARE
                            System.out.println("Su opcion fue la numero 2: Compartir documento");
                            boolean salirShare = false;
                            ArrayList<String> userList = new ArrayList<String>();
                            System.out.println("Permiso de escribir: Escritura");
                            System.out.println("Permiso de lectura: Lectura");
                            System.out.println("Permiso de comentar: Comentario");
                            System.out.println("Considerando lo anterior, ingrese el permiso que desea dar, respetando la mayuscula de la primera letra");
                            input.nextLine();
                            String permiso = input.nextLine();
                            System.out.println("Ingrese el ID del documento que desea compartir: ");
                            Integer documentID = input.nextInt();
                            System.out.println("Ingrese el username del usuario al que desea compartir el documento");
                            input.nextLine();
                            String user = input.nextLine();
                            userList.add(user);
                            while (!salirShare) {
                                System.out.println("¿Desea dar permisos a otro usuario?");
                                System.out.println("1. Dar permisos a otro usuario");
                                System.out.println("2. Finalizar proceso de compartir");
                                try{
                                    System.out.println("Introduzca su eleccion: ");
                                    eleccionShare = input.nextInt();
                                    switch (eleccionShare) {
                                        case 1:
                                            System.out.println("Ingrese el username del usuario al que desea compartir el documento");
                                            input.nextLine();
                                            user = input.nextLine();
                                            userList.add(user);
                                            break;
                                        case 2:
                                            salirShare = true;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("El menu solo admite como entrada numeros y alguna de las opciones anteriores");
                                    input.next();
                                }
                            }
                            controlador.share(userList, documentID, permiso);
                            break;

                        case 3: // ADD
                            System.out.println("Ingrese el ID del documento que desea editar: ");
                            documentID = input.nextInt();
                            System.out.println("Ingrese el texto que desea agregar al final del documento: ");
                            input.nextLine();
                            String newText = input.nextLine();
                            controlador.add(documentID, newText);
                            break;

                        case 4: // ROLLBACK
                            System.out.println("Ingrese el ID del documento del cual desea restaurar una version: ");
                            documentID = input.nextInt();
                            System.out.println("Ingrese el ID de la version que desea restaurar del documento: ");
                            Integer versionID;
                            versionID = input.nextInt();
                            controlador.rollback(documentID, versionID);
                            break;

                        case 5: // REVOKEACCESS
                            System.out.println("Ingrese el ID del documento del cual desea revocar accesos: ");
                            documentID = input.nextInt();
                            controlador.revokeAccess(documentID);
                            break;

                        case 6: // SEARCH
                            System.out.println("Ingrese el texto que desea buscar en los documentos donde tenga acceso");
                            input.nextLine();
                            String searchText = input.nextLine();
                            controlador.search(searchText);
                            break;

                        case 7: //VISUALIZE
                            controlador.visualize();
                            break;

                        case 8: // DELETE
                            System.out.println("Ingrese el ID del documento que desea editar: ");
                            documentID = input.nextInt();
                            System.out.println("Ingrese el numero de caracteres que desea eliminar ");
                            Integer textSize = input.nextInt();
                            controlador.delete(documentID, textSize);
                            break;

                        case 9: // SEARCHANDREPLACE
                            System.out.println("Ingrese el ID del documento que desea editar: ");
                            documentID = input.nextInt();
                            System.out.println("Ingrese el texto que desea buscar en el documento: ");
                            input.nextLine();
                            searchText = input.nextLine();
                            System.out.println("Ingrese el texto por el cual desea reemplazar el texto anterior: ");
                            String replaceText = input.nextLine();
                            controlador.searchAndReplace(documentID, searchText, replaceText);
                            break;

                        case 10: // LOGOUT
                            controlador.logout();
                            break;

                        case 11: // CERRAR PROGRAMA
                            System.out.println("Gracias por utilizar " + controlador.getEditor().getName());
                            salirMenu = true;
                            input.close();
                            break;

                        default: // OTROS NUMEROS QUE NO ESTAN EN EL MENU
                            System.out.println("Seleccione nuevamente una de las opciones anteriores");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("El menu solo admite como entrada numeros y alguna de las opciones anteriores");
                    input.next();
                }
            }
        }
    }
}
