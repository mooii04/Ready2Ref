import { EntrenamientoModel } from './entrenamiento.model';
import { MensajeModel } from './mensaje.model';
import { PackModel } from './pack.model';
import { ReciboModel } from './recibo.model';
import { UserProfile } from './user.model';

export type Categoria =
  | 'AUXILIAR' | 'OFICIAL' | 'PROVINCIAL' | 'DIVISION_HONOR'
  | 'ASISTENTE_3RFEF' | 'RFEF3' | 'ASISTENTE_2RFEF' | 'RFEF2'
  | 'ASISTENTE_1RFEF' | 'RFEF1' | 'ASISTENTE_SEGUNDA' | 'SEGUNDA'
  | 'ASISTENTE_PRIMERA' | 'PRIMERA';

export type Talla = 'XS' | 'S' | 'M' | 'L' | 'XL' | 'XXL';

export interface ArbitroModel extends UserProfile {
  fechaNacimiento: string;
  edad: number;
  categoria: Categoria;
  fechaInscripcion: string;
  tallaBotas: number;
  tallaCamiseta: Talla;
  tallaCalzonas: Talla;
  tallaChandal: Talla;
  foto: string;
  asistencias?: AsistenciaModel[];
  recibos?: ReciboModel;
  pack?: PackModel;
  mensajesArbitro?: MensajeModel[];
}

export interface AsistenciaModel {
  idAsistencia: string;
  arbitro?: ArbitroModel;
  entrenamiento?: EntrenamientoModel;
}
