import { Tipo } from "./tipo";
import { Status } from "./status";

export class Material{
      id: number;
      nome: string;
      descricao: string;
      tipo: Tipo = new Tipo();
      tipoDescricao: string;
      codigobarras: string;
      categoria: string;
      modelo: string;
      status:Status = new Status();
      statusDescricao: string;
      temDevolucao: boolean; 
      temCodigobarras: boolean;
      lancamentos: any[];
      itens: any[];
  }