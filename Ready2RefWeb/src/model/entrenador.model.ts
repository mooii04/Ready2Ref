import { UserProfile } from './user.model';
import { EntrenamientoModel } from './entrenamiento.model';
import { MensajeModel } from './mensaje.model';

export interface EntrenadorModel extends UserProfile {
  entrenamientos?: EntrenamientoModel[];
  mensajesEntrenador?: MensajeModel[];
}
