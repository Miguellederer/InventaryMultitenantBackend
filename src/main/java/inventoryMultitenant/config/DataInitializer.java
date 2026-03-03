package inventoryMultitenant.config;

//El DataInitializer es una clase que se ejecuta al iniciar la aplicación para cargar datos iniciales en la base de datos. En este caso, se encarga de crear permisos para diferentes módulos y acciones, así como un rol de administrador que tiene acceso a todos los permisos. Esto es útil para asegurar que la aplicación tenga una configuración básica de seguridad desde el principio, permitiendo a los administradores gestionar los permisos y roles de manera efectiva.

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import inventoryMultitenant.enums.Accion;
import inventoryMultitenant.permisos.model.PermisosModel;
import inventoryMultitenant.permisos.repository.PermisosRepository;
import inventoryMultitenant.roles.model.RolesModel;
import inventoryMultitenant.roles.model.RolesPermisos;
import inventoryMultitenant.roles.repository.RolesPermisoRepository;
import inventoryMultitenant.roles.repository.RolesRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PermisosRepository permisosRepository;
    private final RolesRepository rolesRepository;
    private final RolesPermisoRepository rolesPermisoRepository;

    private final List<String> MODULOS = List.of(
            "ALMACENES",
            "CENTROS",
            "EMPRESAS",
            "PERMISOS",
            "PRODUCTOS",
            "ROLES",
            "USERS",
            "STOCK");

    @Override
    public void run(String...args){
        if(permisosRepository.count() > 0 ){
            return;
        }

        for (String modulo:MODULOS){
            for(Accion accion: Accion.values()){
                PermisosModel permiso = new PermisosModel();
                permiso.setModulo(modulo);
                permiso.setAccion(accion);
                permiso.setCodigo(modulo + "_" + accion.name());
                permiso.setDescripcion("Permiso para " + accion.name() + " en el módulo " + modulo);

                permisosRepository.save(permiso);
            }
        }

        RolesModel admin = new RolesModel();
        admin.setNombre("ADMIN");
        admin.setDescripcion("Rol Acceso a todo el Sistema");
        rolesRepository.save(admin);

        List<PermisosModel> permisos = permisosRepository.findAll();

        for(PermisosModel permiso: permisos){
            RolesPermisos rp = new RolesPermisos();
            rp.setRole(admin);
            rp.setPermiso(permiso);
            rolesPermisoRepository.save(rp);
        }

        System.out.println("Permisos y Rol ADMIN inicializados correctamente.");
    }
}
