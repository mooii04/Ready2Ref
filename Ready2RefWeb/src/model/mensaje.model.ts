import { ArbitroModel } from './arbitro.model';
import { EntrenadorModel } from './entrenador.model';

export interface MensajeModel {
  id: string;
  asunto: string;
  contenido: string;
  fechaEnvio: string;
  leido: boolean;
  arbitros?: ArbitroModel[];
  entrenadores?: EntrenadorModel[];
}
