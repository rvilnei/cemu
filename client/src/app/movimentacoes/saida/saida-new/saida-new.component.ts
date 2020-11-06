import { Router } from '@angular/router';
import { MovimentacoesService } from './../../movimentacoes.service';
import { Observable } from 'rxjs';
import { UserService } from './../../../core/user/user.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/core/user/user';

@Component({
  selector: 'app-saida-new',
  templateUrl: './saida-new.component.html',
  styleUrls: ['./saida-new.component.css']
})
export class SaidaNewComponent implements OnInit {
  movimentacaoForm: FormGroup;
  user$: Observable<User>;
  usuario: User;
  unidades$: Observable<any[]>; // para usar | async
  materiaisEstoque =[];

  constructor(
      private movimentacoesService: MovimentacoesService,
      private formBuilder: FormBuilder,
      private userService: UserService,
      private router: Router
  ) { 
    this.user$ = this.userService.getUser();
  }
  // listaMateriaisEstoque(){
  //   this.movimentacoesService.getListaMateriaisEstoque().subscribe( estoque => {
  //     this.materiaisEstoque = estoque;
  //     this.materiaisEstoque.filter( (estque) => estque.quantidade > 0) ;
  //     this.materiaisEstoque.sort((a, b) => a.materialId.localeCompare(b.materialId))
  //   } );
  //   return this.materiaisEstoque;
  // }
  
  listaMateriaisEstoque(){
    this.movimentacoesService.getListaEstoqueUnidade( this.usuario.unidadeId ).subscribe( estoque => {
      this.materiaisEstoque = estoque;
      this.materiaisEstoque.filter( (estque) => estque.quantidade > 0) ;
      this.materiaisEstoque.sort((a, b) => a.materialId.localeCompare(b.materialId))
    } );
    return this.materiaisEstoque;
  }

  ngOnInit() {
    this.unidades$ = this.movimentacoesService.getUnidades();
    this.user$.subscribe(
      user => {this.usuario = user; }
    );
    this.movimentacaoForm = this.formBuilder.group({
      unidadeorigemId: [ this.usuario.unidadeId, Validators.required],
      unidadedestinoId: [ null, Validators.required],
      datacriacao: [ null, Validators.required],
      datarecebimento: [ null, Validators.required],
      observacao: [ null, Validators.required],
      itens: [ null, Validators.required],
      movimentacao_id: [ null, Validators.required],
      material: [ null, Validators.required]
    });
    this.listaMateriaisEstoque();
  }
  movimentacao;
  buttonDisabled = false;
  onSubmit(){
   this.buttonDisabled = true;
    this.movimentacaoForm.value.itens =  this.itens;
    this.movimentacoesService.addMovimentacao(this.movimentacaoForm, this.itens)
    .subscribe(movimentacao => {
      this.movimentacao = movimentacao; 
      this.itens = [];
      this.listaMateriaisEstoque();
      this.router.navigate([ '/movimentacoes', this.usuario.unidadeId, 'saida'  ] );
  })
}
itens: any[];
atualizaItens(itens: any[]){
 this.itens = itens;
}

}
