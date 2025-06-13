import { EntrenadorModel } from './entrenador.model';
import { AsistenciaModel } from './arbitro.model';

export interface EntrenamientoModel {
  idEntrenamiento: string;
  fecha: string;
  asistencias?: AsistenciaModel[];
  entrenador?: EntrenadorModel;
}
