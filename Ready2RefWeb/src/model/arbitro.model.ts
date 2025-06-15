// src/app/models/arbitro.model.ts

export interface GetAsistenciaDto {
  // Ajusta los campos según el DTO real
  id: number;
  fecha: string;
  tipo: string;
  descripcion?: string;
}

export interface GetDtoPack {
  id: number;
  nombre: string;
  descripcion?: string;
  fechaEntrega?: string;
}

export interface Arbitro {
  nombre: string;
  primerApellido: string;
  segundoApellido: string;
  username: string;
  email: string;
  telefono: string;
  fechaNacimiento: string;
  edad: number;
  categoria: string;
  fechaInscripcion: string;
  tallaBotas: number;
  tallaCamiseta: string;
  tallaCalzonas: string;
  tallaChandal: string;
  foto: string;
  asistencias?: GetAsistenciaDto;
  pack?: GetDtoPack;
}
