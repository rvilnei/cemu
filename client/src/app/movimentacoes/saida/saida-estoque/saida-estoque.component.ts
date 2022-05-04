import { Observable } from 'rxjs';
import { MovimentacoesService } from './../../movimentacoes.service';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { NgScrollbar } from 'ngx-scrollbar';

@Component({
  selector: 'app-saida-estoque',
  templateUrl: './saida-estoque.component.html',
  styleUrls: ['./saida-estoque.component.css']
})
export class SaidaEstoqueComponent implements OnInit {
  
  @Output() itens: EventEmitter<any[]> = new EventEmitter();
  @Input() materiaisEstoque =[];
  @Input() usuario;
  constructor( private movimentacoesService: MovimentacoesService ) { }

  itemMaterialLista:Array<any>=[];
  addItemMaterial(itemMaterial){
   itemMaterial.situacao = "ITEM_PENDENTE";
   this.itemMaterialLista.unshift(itemMaterial);
   let index = this.materiaisEstoque.indexOf(itemMaterial);
   let removidoItem = this.materiaisEstoque.splice(index, 1 );
   this.itens.emit( this.itemMaterialLista );
  }

  deleteItem(item){
    let index = this.itemMaterialLista.indexOf(item) ;
    let removidoItem = this.itemMaterialLista.splice(index, 1) ;
    this.materiaisEstoque.unshift(removidoItem[0]);
    this.materiaisEstoque.sort((a, b) => a.materialId.localeCompare(b.materialId))
    this.itens.emit( this.itemMaterialLista );
  }

  verificaQuantidade( envento, quantidade ){
    let valorInformado: number = envento.target.value ;
    let qtdItemMaterial: number = quantidade ;
      if ( valorInformado <= 0 ) {
        envento.target.value = null;
      }
      if ( valorInformado > qtdItemMaterial ) {
        envento.target.value = null ;
      }
  }

  ngOnInit() {
  //  this.listaMateriaisEstoque();
   // this.itemMaterialLista  = [];
  }

}
