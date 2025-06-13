export type UserRole = 'USER' | 'ADMIN' | 'ENTRENADOR';

export interface UserProfile {
  id: string;
  nombre: string;
  primerApellido: string;
  segundoApellido: string;
  username: string;
  email: string;
  telefono: string;
  password: string;
  roles: UserRole[];
  enabled: boolean;
  activationToken: string;
  createdAt: string;
}
