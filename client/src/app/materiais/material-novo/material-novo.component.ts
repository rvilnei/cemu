
import { TipoLancamento } from './../../shared/enum/tipo-lancamento';
import { UserService } from './../../core/user/user.service';
import { User } from './../../core/user/user';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { MateriaisService } from './../materiais.service';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { TypeaheadMatch } from 'ngx-bootstrap/typeahead/typeahead-match.class';
import { Location } from '@angular/common';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-material-novo',
  templateUrl: './material-novo.component.html',
  styleUrls: ['./material-novo.component.css']
})
export class MaterialNovoComponent implements OnInit {

  material: any = {
    tipo: {id: null , nome: ''},
    status: {id: null , nome: ''}
  };
  materialTipo$: Observable<any[]>; // para usar | async
  materialStatu$: Observable<any[]>;
  materiais$: Observable<any[]>;
  noResult: any;
  public modalRef: BsModalRef;
  materialselect: any;
  itemMaterial: any = {};
  user: User;
  materialForm: FormGroup;
  fieldsetDisabled: boolean = false;

  currentPage: number = 1;
  numPages: number = 0;
  itemsPerPage = 3;

  fileContent = [];
  file: any;
  tipoMaterial: String = '';

  constructor(
    private service: MateriaisService,
    private router: Router,
    private modalService: BsModalService,
    private location: Location,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.materialTipo$ = this.service.getTipo();
    this.materialStatu$ = this.service.getStatus();
    this.materiais$ = this.service.getMateriais();
    this.userService.getUser()
      .subscribe(user => {
        this.user = user;
      });
  }

  save(): void {
    if (this.material.id) {
      this.service.updateMaterial(this.material)
        .subscribe(() => {
          this.service.getMaterial(this.material.id)
            .subscribe(material => {
              this.material = material;
              this.router.navigate(['/materialDetail', this.material.id]);
            });
        })
    } else {
      this.service.addMaterial(this.material)
        .subscribe(material => {
          this.material = material;
          this.router.navigate(['/materialDetail', this.material.id]);
        })
    }
  }

  limpar() {
    this.fieldsetDisabled = false;
    this.material = {};
  }

  onSelect(event: TypeaheadMatch): void {
    this.fieldsetDisabled = true;
    this.material = event.item;
  }

  setIsEdit(): boolean {
    return this.material.id ? true : false
  }

  typeaheadNoResults(event: boolean): void {
    this.noResult = event;
  }

  openModal(template: TemplateRef<any>, materialselect) {
    this.modalRef = this.modalService.show(template); // {3}
    this.materialselect = materialselect;
    this.itemMaterial = { quantidade: 1 };
  }

  voltar(): void {
    this.location.forward();
  }

  private onSubmitMaterialWithItem(material: any, itemMaterial: any): any {
    this.service.addMaterial(material)
      .subscribe(material => {
        this.service.addLancamentoItemMaterial(material, itemMaterial)
          .subscribe(material => {
            this.service.getMaterial(material.id)
              .subscribe(material => { this.material = material; this.materialselect = material });
          })
      })
  }

  onSubmitItemMaterial() {
    this.itemMaterial.matricula = this.user.matricula;
    this.itemMaterial.unidadeorigemId = this.user.unidadeId;
    this.itemMaterial.unidadedestinoId = this.user.unidadeId;
    this.itemMaterial.tipo = TipoLancamento.CADASTRO;
    if (!this.materialselect.id) {
      this.service.addMaterial(this.materialselect)
        .subscribe(material => {
          this.service.addLancamentoItemMaterial(material, this.itemMaterial)
            .subscribe(lancamento => {
              this.service.getMaterial(material.id)
                .subscribe(material => {
                //  this.isEdit = this.setIsEdit();
                  this.materiais$ = this.service.getMateriais();
                });
            })
        })
    } else {
      this.service.addLancamentoItemMaterial(this.materialselect, this.itemMaterial)
        .subscribe(material => {
          this.service.getMaterial(this.materialselect.id)
            .subscribe(material => {
              this.materiais$ = this.service.getMateriais();
            });
        })
    }
    this.material = {};
    this.router.navigate(['/materiais']);
  }

  changeMaterialTipo(e) {
    this.tipoMaterial = this.material.tipo.id == 2 ? 'SUPRIMENTO' : 'PECA_REPOSICAO';
   // this.tipoMaterial = this.material.tipo.nome;
    if (this.tipoMaterial == 'PECA_REPOSICAO') {
      this.material.codigobarras = '';
      this.material.temDevolucao = false;
    } else if (this.tipoMaterial == 'SUPRIMENTO') {
      this.material.temDevolucao = true;
    }
  }

  changeTemDevolucao(e) {
    this.tipoMaterial = this.material.tipo.id == 2 ? 'SUPRIMENTO' : 'PECA_REPOSICAO';
   // this.tipoMaterial = this.material.tipo.nome;
    if (this.material.temDevolucao) {
      this.material.tipo.id = 2; // SUPRIMENTO
    } else {
      this.material.tipo.id = 1; // PEÇA_REPOSIÇÃO
      this.material.status = null ;
      this.material.temCodigobarras = false;
    }
  }

  changeTemCodigoBarras(e) {
    if (e.value = true) {
      this.material.temDevolucao = true;
      this.material.tipo.id = 2; // SUPRIMENTO
    }
  }

  onChengeFile(event) {
    this.currentPage = 1;
    this.file = event.target.files[0];
    const selectedFiles = <FileList>event.srcElement.files;
    document.getElementById('fileLabel').innerHTML = selectedFiles[0].name;
    this.fileReadContent(this.file);
  }

  fileReadContent(arquivo: File) {
    let fileReader = new FileReader();
    fileReader.onload = (e) => {
      let dataURL = fileReader.result as string;
      let output = document.getElementById('output');
      let arrayCodBarras = dataURL.split(" ");
      arrayCodBarras.forEach((e) => {
        let codBarras = { codigo: e, confirmar: true };
        this.fileContent.push(codBarras);
      });
    }
    fileReader.readAsText(this.file);
  }

  onSubmitMaterialCodBarrasArquivo() {
    this.fileContent.forEach((item) => {
      let codBarra = item.codigo;
      this.material.codigobarras = codBarra;
      this.service.addMaterial(this.material)
        .subscribe(material => {
          this.itemMaterial.quantidade = 1;
          this.itemMaterial.matricula = this.user.matricula;
          this.itemMaterial.unidadeorigemId = this.user.unidadeId;
          this.itemMaterial.unidadedestinoId = this.user.unidadeId;
          this.itemMaterial.tipo = TipoLancamento.CADASTRO;
          this.service.addLancamentoItemMaterial(material, this.itemMaterial)
            .subscribe(material => {
            })
        })
    });
  }
}