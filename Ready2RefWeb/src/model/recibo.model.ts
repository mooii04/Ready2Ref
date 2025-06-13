import { ArbitroModel } from './arbitro.model';
import { PackModel } from './pack.model';

export type MetodoPago = 'TARJETA' | 'TRANSFERENCIA' | 'BIZUM';

export interface ReciboModel {
  id: string;
  cantidad: number;
  concepto: string;
  fechaPago: string;
  metodoPago: MetodoPago;
  arbitro?: ArbitroModel;
  pack?: PackModel;
}
