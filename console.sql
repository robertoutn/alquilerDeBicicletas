select * from ESTACIONES order by 1 desc ;
insert into ESTACIONES (NOMBRE, FECHA_HORA_CREACION, LATITUD, LONGITUD)
values ('n','2023-10-03 21:25:12',-1,-1);
select * /*delete*/ from ALQUILERES where ESTACION_DEVOLUCION >18;
select * /*delete*/ from ALQUILERES where ID=1;
select * from ESTACIONES where ID=1;
    delete from ESTACIONES where ID=1;
select * from TARIFAS where DIA_MES=18;
update ESTACIONES
set  ID=1,
     NOMBRE='UTN',
     FECHA_HORA_CREACION='2023-10-03 21:22:30',
     LATITUD=-31.442961123175007,
     LONGITUD=-64.19409112111947
where id=1;
update ESTACIONES
set  NOMBRE='Comedor',
     FECHA_HORA_CREACION='2023-10-03 21:22:30',
     LATITUD=-31.439612445060444,
     LONGITUD=-64.189333639292
where id=2;

select t.* from ESTACIONES t
where 1 = 1
            and t.NOMBRE LIKE '%U%'
            order by t.NOMBRE;
select * from ESTACIONES t
    where t.FECHA_HORA_CREACION > :fecha/*'2023-10-01'*/
    and t.LATITUD> -34.4
order by t.FECHA_HORA_CREACION
limit 4;
select * from ESTACIONES
delete from ESTACIONES
where ID>18;
