import { Tipo } from "./tipo";
import { Status } from "./status";

export class Material{
    id: number;
    nome: string;
    descricao: string;
    tipoId: number;
    codigobarras: string;
    categoria: number;
    modelo: string;
    statusId: number;
    temDevolucao: boolean;
    temCodigobarras: boolean;
  }