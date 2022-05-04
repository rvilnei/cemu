import { TipoLancamento } from './../../shared/enum/tipo-lancamento';
import { UserService } from './../../core/user/user.service';
import { User } from './../../core/user/user';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { MateriaisService } from './../materiais.service';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
//import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { TypeaheadMatch } from 'ngx-bootstrap/typeahead/typeahead-match.class';
import { Location } from '@angular/common';
import { TabDirective } from 'ngx-bootstrap/tabs';
import { TabsetComponent } from 'ngx-bootstrap'

@Component({
  selector: 'app-material-new',
  templateUrl: './material-new.component.html',
  styleUrls: ['./material-new.component.css']
})
export class MaterialNewComponent implements OnInit {
  material: any = {};
  materialBkp: Object = {};
  materialTipo$: Observable<any[]>; // para usar | async
  materialStatu$: Observable<any[]>;
  nomeMaterial: string[];
  materiais$: Observable<any[]>;
  optionOnBlur: any;
  noResult: any;
  selected: string;
  isEdit: boolean;
  public modalRef: BsModalRef;
  materialselect: any;
  itemMaterial:any = {};
  //user$: Observable<User>;
  user: User;

  @ViewChild('staticTabs', { static: true }) staticTabs: TabsetComponent;

  constructor(  
    private service: MateriaisService,
    private router: Router,
    private modalService: BsModalService ,
    private location: Location,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.materialTipo$ = this.service.getTipo();
    this.materialStatu$ = this.service.getStatus();
    this.materiais$ = this.service.getMateriais();

    this.userService.getUser()
        .subscribe( user =>{
          this.user = user;
        } );

  //  this.selectTabIndex(0);
    this.idTab = 'semCodBarras' ;
    this.initMaterialTab();
  }



  save(): void {
    if ( this.material.id ) {
    this.service.updateMaterial(this.material)
      .subscribe( ( ) => {
        this.service.getMaterial(this.material.id)
          .subscribe( material => { this.material  = material; 
             this.router.navigate([ '/materialDetail', this.material.id  ] );
        });
      })
    } else {
      this.service.addMaterial(this.material)
      .subscribe(material => {
          this.material = material; 
          this.router.navigate([ '/materialDetail', this.material.id  ] );
      })
    }
  }

  onSelect(event: TypeaheadMatch): void {
    this.material = event.item;
    this.materialBkp= JSON.parse(JSON.stringify(this.material ));
    this.selectTabIndex();
  }

  selectTabIndex(){
    if(this.material.codigobarras || this.material.temCodigobarras ){
      this.staticTabs.tabs[1].active = true;
    } else {  
      this.staticTabs.tabs[0].active = true;
    } 
    this.isEdit = this.setIsEdit();
  }


  idTab: string;
  valueTab: string;
  onSelectTab(data: TabDirective): void {
    this.idTab = data.id;
    this.valueTab = data.heading;
    this.initMaterialTab();
   // if(this.material.id && this.isEdit) this.material = {};
  }

  initMaterialTab(){

    if (this.material.id)
      this.material= JSON.parse(JSON.stringify(this.materialBkp ));
    console.log('***** *** ***'+this.idTab  );
    if ( this.idTab == 'semCodBarras' ){
      if(this.material.id){
      //  this.material = {};
        this.isEdit = this.setIsEdit();
      }
      this.material.temCodigobarras = false;
      this.material.codigobarras = '';
      this.material.temDevolucao = true;
    } else if( this.idTab == 'comCodBarras') {
      if(this.material.id){
      //  this.material = {};
        this.isEdit = this.setIsEdit();
      }
      this.material.temCodigobarras = true;
      this.material.tem_devolucao = true;
      this.material.tipo = 2;
    }

  }

  setIsEdit(): boolean {
    return this.material.id ? true : false
  }

  typeaheadNoResults(event: boolean): void {
    this.noResult = event;
  }

  onClick(){
    this.selected = '';
   // this.material = {};
   // this.initMaterialTab();
    this.isEdit = this.setIsEdit();
  }

  openModal(template: TemplateRef<any>, materialselect) {
    this.modalRef = this.modalService.show(template); // {3}
    this.materialselect = materialselect;
    this.itemMaterial = {quantidade: 1};
    // if(this.materialselect.temCodigobarras){
    //   this.itemMaterial.quantidade = 1;
    // }
  }
 
  voltar(): void{
    this.location.forward();
  }

 private onSubmitMaterialWithItem (material: any, itemMaterial: any): any{
  this.service.addMaterial(material)
  .subscribe(material => {
   console.log('***** onSubmitMaterialWithItem   ');
  //his.itemMaterial.quantidade = 1; 
       this.service.addLancamentoItemMaterial( material,itemMaterial )
       .subscribe(material => {             
            this.service.getMaterial(material.id)
                        .subscribe(material => { this.material  = material; this.materialselect  = material });
        })
    })
}

  onSubmitItemMaterial(){
    this.itemMaterial.matricula = this.user.matricula;
    // this.itemMaterial.unidadeorigem = this.user.unidade;
    // this.itemMaterial.unidadedestino = this.user.unidade;
    this.itemMaterial.unidadeorigemId = this.user.unidadeId;
    this.itemMaterial.unidadedestinoId = this.user.unidadeId;
    
    this.itemMaterial.tipo = TipoLancamento.CADASTRO;
    console.log("tipo ; "+this.itemMaterial.tipo);
    console.log("tipo enum ; "+TipoLancamento.CADASTRO);
    console.log('**** UNIDADE : '+this.itemMaterial.unidadeorigem+" matricula : "+this.user.matricula );
     if  (!this.materialselect.id){
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
                           //   this.material  = material; 
                           //   this.materialselect  = material;
                              this.isEdit = this.setIsEdit();
                              this.materiais$ = this.service.getMateriais();
                            });
             })
         })
     } else {  console.log('**** COM ID ***');
      this.service.addLancamentoItemMaterial(this.materialselect, this.itemMaterial )
      .subscribe(material => {        
          this.service.getMaterial(this.materialselect.id)
                      .subscribe(material => { 
                      //  this.material  = material; 
                      });
      })
    }
    this.material = {};
    this.router.navigate([ '/materiais' ] );
  }

  tipoMaterial: String = '';
  changeMaterialTipo(e){
    console.log("**** tipochange"+this.material.tipo);
    this.tipoMaterial = this.material.tipo.id == 2 ? 'SUPRIMENTO' : 'PECA_REPOSICAO';
    console.log("**** tipochange"+this.tipoMaterial);
    if( this.tipoMaterial == 'PECA_REPOSICAO') {
      this.material.tem_rfid = false;
      this.material.codigobarras = '';
      this.material.tem_devolucao = false;
    } else if( this.tipoMaterial == 'SUPRIMENTO') {
      this.material.tem_devolucao = true;
    }
  }


  file:any;
  onChengeFile(event){
    this.currentPage = 1;
    this.file = event.target.files[0];
    const selectedFiles = <FileList>event.srcElement.files;
    document.getElementById('fileLabel').innerHTML = selectedFiles[0].name;
    this.fileReadContent(this.file );
  }

  currentPage:number = 1;
  numPages:number = 0;
  itemsPerPage = 3;

 // arqvuivoCodBaraas = [];
  fileContent = [];
  fileReadContent(arquivo: File) {
      let fileReader = new FileReader();
      fileReader.onload = (e) => {
        let dataURL = fileReader.result as string;
        //const dataURL: string | ArrayBuffer = fileReader.result;
        let output = document.getElementById('output');
        let arrayCodBarras = dataURL.split(" " );
          arrayCodBarras.forEach( (e) => {
              let codBarras = { codigo: e, confirmar: true };
              this.fileContent.push(codBarras);
          } );
      }
          fileReader.readAsText(this.file);
  }
  
  onSubmitMaterialCodBarrasArquivo(){
    this.fileContent.forEach( (item) => {
      let codBarra =  item.codigo ; 
      this.material.codigobarras = codBarra;
       console.log('*****  '+this.material.nome+' *** '+this.material.codigobarras);
     //  this.save();
       this.service.addMaterial(this.material)
       .subscribe(material => {
        console.log('***** incuso  '+material.nome+' *** '+material.codigobarras);
          this.itemMaterial.quantidade = 1; 
          this.itemMaterial.matricula = this.user.matricula;
          this.itemMaterial.unidadeorigemId = this.user.unidadeId;
          this.itemMaterial.unidadedestinoId = this.user.unidadeId;
       
          this.itemMaterial.tipo = TipoLancamento.CADASTRO;
          this.service.addLancamentoItemMaterial( material, this.itemMaterial )
            .subscribe(material => {        
              //  this.service.getMaterial(this.materialselect.id)
              //              .subscribe(material => { this.material  = material; });
            })

         //  this.material = material; 
          // this.router.navigate([ '/materialDetail', this.material.id  ] );
       })
    } );
  }

}