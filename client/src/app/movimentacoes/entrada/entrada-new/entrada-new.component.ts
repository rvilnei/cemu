import { filter } from 'rxjs/operators';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Component, OnInit, TemplateRef, Input } from '@angular/core';
import { MovimentacoesService } from 'src/app/movimentacoes/movimentacoes.service';

@Component({
  selector: 'app-movimentacao-entrada-new',
  templateUrl: './entrada-new.component.html',
  styleUrls: ['./entrada-new.component.css']
})
export class EntradaNewComponent implements OnInit {
  public modalRef: BsModalRef; 
  @Input() movimentacao: any;

  constructor(
    private modalService: BsModalService,
    private movimentacoesService: MovimentacoesService
  ) { }

  ngOnInit() {
  }
  

  public openModal(template: TemplateRef<any>) {
   // this.movimentacao = {nome: "vilnei"};
   console.log( this.movimentacao );
    this.modalRef = this.modalService.show(template); // {3}
    this.modalRef.setClass('modal-lg');
  }

  public receberItemMovimentacao( itens ){
      itens = itens.filter( item => item.situacao === "ITEM_ENVIADO" );
      console.log(" ********receberItemMovimentacao" );
      console.log(itens);
  }

// public itemNaoRecebido(item){
  public itemNaoRecebido(movimentacao, item){
  //item = movimentacao.itens.filter( i => i.id === item.id);
  item.situacao = "ITEM_NAO_RECEBIDO";
  movimentacao.status = "MOVI_EM_ANDAMENTO";
  // this.http.post<any>(this.API+'movimentacoes', this.movimentacao , httpOptions).pipe(
  console.log(" ********itemNaoRecebido" );
    console.log(item);
    console.log("movimentacao : ");
    console.log(movimentacao);

    this.movimentacoesService.itemNaoRecebido( movimentacao, item)
    .subscribe(itemMovimentacao => {
      console.log( "update item: "+itemMovimentacao.id );
      console.log( "update situacao: "+itemMovimentacao.situacao );
     // this.movimentacao = movimentacao; 
    //  this.itens = [];
     // this.listaMateriaisEstoque();
     // this.router.navigate([ '/movimentacoes', this.usuario.unidadeId, 'saida'  ] );
  })
}
  itensMovimentacao: any[] ;
  public receberItens(movimentacao){
    console.log( "chegando ***  aqui" );
    this.itensMovimentacao = movimentacao.itens;
    movimentacao.status = "MOVI_CONCLUIDA";
    this.itensMovimentacao.forEach(element => {
      console.log( "chegando aqui" );
      if( element.situacao == "ITEM_PENDENTE" ){
        element.situacao = "ITEM_RECEBIDO";
      }
    });
    this.movimentacoesService.receberItens( movimentacao)
    .subscribe(itemMovimentacao => {
      console.log( "update item: "+itemMovimentacao.id );
      console.log( "update situacao: "+itemMovimentacao.situacao );
    })

  }

}
