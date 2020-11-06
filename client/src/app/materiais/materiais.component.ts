import { TipoLancamento } from './../shared/enum/tipo-lancamento';
import { UserService } from './../core/user/user.service';
import { User } from './../core/user/user';
import { async } from '@angular/core/testing';
import { Router } from '@angular/router';

import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Observable } from 'rxjs';
import { MateriaisService } from './materiais.service';
import { MATERIAIS } from './../shared/materiais';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-materiais',
  templateUrl: './materiais.component.html',
  styleUrls: ['./materiais.component.css'],
  preserveWhitespaces: true // espaço entre os botoes
})
export class MateriaisComponent implements OnInit {

  //materiais: any[] = MATERIAIS;
  //materiais: any[];
  materiais$: Observable<any[]>; // para usar | async
  materialTipo$: Observable<any[]>; // para usar | async
  public modalRef: BsModalRef;
  materialselect: any;
  itemMaterial:any = {};
  user: User;
  
  currentPage:number = 1;
  numPages:number = 0;
  itemsPerPage = 8;

  constructor( 
          private service: MateriaisService,
          private modalService: BsModalService ,
          private router: Router,
          private location: Location,
          private userService: UserService
          
        ) { }

  ngOnInit() {
    /** substitui para usar | async n view **/
    this.materiais$ = this.service.getMateriais();
   // this.materialTipo$ = this.service.getTipo();
   this.userService.getUser()
   .subscribe( user =>{
     this.user = user;
   } );

  }

  delete(material: any): void {
    this.service.deleteMaterial(material)
      .subscribe( retorno => {
          this.materiais$ = this.service.getMateriais();
          this.router.navigate([ '/materiais'] , { replaceUrl: true } );
      } )
  }
  
  openModal(template: TemplateRef<any>, materialselect) {
    this.modalRef = this.modalService.show(template); // {3}
    //this.materialselect = materialselect;
    this.materialselect = Object.assign({}, materialselect);
    this.itemMaterial = {quantidade: 1};
    this.materialselect.codigobarras = "";
  }

  onSubmitItemMaterial(){
    console.log("**inicio** ; ");
    this.itemMaterial.matricula = this.user.matricula;
    this.itemMaterial.unidadeorigemId = this.user.unidadeId;
    this.itemMaterial.unidadedestinoId = this.user.unidadeId;
    this.itemMaterial.tipo = TipoLancamento.CADASTRO;
    console.log("tipo ; "+this.itemMaterial.tipo);
    console.log("tipo enum ; "+TipoLancamento.CADASTRO);
    console.log('**** UNIDADE : '+this.itemMaterial.unidadeorigem+" matricula : "+this.user.matricula );
    //  if  (!this.materialselect.id){
      if  (this.materialselect.codigobarras || this.materialselect.temCodigobarras){
       console.log('**** SEM ID');
       this.service.addMaterial(this.materialselect)
       .subscribe(material => {
       // console.log('***** onSubmitMaterialWithItem   '+material);
       //his.itemMaterial.quantidade = 1; 
            this.service.addLancamentoItemMaterial( material,this.itemMaterial )
            .subscribe( lancamento => {    
        //      console.log('***** material.id '+material);         
                  this.service.getMaterial(material.id)
                     .subscribe(material => { 
                                              this.materialselect  = material;
                                              this.materiais$ = this.service.getMateriais();
                                            });
             })
         })
     } else {  console.log('**** COM ID ***');
      this.service.addLancamentoItemMaterial(this.materialselect, this.itemMaterial )
      .subscribe(material => {        
          this.service.getMaterial(this.materialselect.id)
                      .subscribe();
      })
    }
  }

  voltar(): void{
    this.location.forward();
  }

}
