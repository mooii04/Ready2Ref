import { ArbitroModel } from './arbitro.model';

export interface PackModel {
  id: string;
  nombre: string;
  descripcion: string;
  precio: number;
  arbitros?: ArbitroModel[];
}
