
use alumnos04;

CREATE TABLE grupo (
    idgrupo INT PRIMARY KEY
);

CREATE TABLE alumno (
    NIA INT PRIMARY KEY,             
    Nombre VARCHAR(50),
    Apellidos VARCHAR(100),
    Genero VARCHAR(20),
    Fecha_nacimiento DATE,
    Ciclo VARCHAR(50),
    Curso VARCHAR(20),              
    Grupo INT,                       
    
    CONSTRAINT fk_alumno_grupo       
    FOREIGN KEY (Grupo)
    REFERENCES grupo(idgrupo)
);