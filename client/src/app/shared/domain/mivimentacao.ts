import { ItemMovimentacao } from './item-movimentacao';
export class Movimentacoa {
    id: string //number;
    dtconfirmacaorecebimento: Date;
    dtmovimentacao: Date;
    observacao: string;
    guia_id: number;
    unidadeorigem_id: number;
    unidadedestino_id: number;
    codmovimentacao: number;
    itens_movimentacao: ItemMovimentacao[];
}