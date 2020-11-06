import { Observable } from 'rxjs';
import { MateriaisService } from './../materiais.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

import { MATERIAIS } from './../../shared/materiais';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { switchMap } from 'rxjs/operators';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Location } from '@angular/common';

@Component({
  selector: 'app-material-detail',
  templateUrl: './material-detail.component.html',
  styleUrls: ['./material-detail.component.css']
})
export class MaterialDetailComponent implements OnInit {

  materiais: any[] = [];
  material: any = {};
  material$: Observable<any>;
  public modalRef: BsModalRef; 

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: MateriaisService,
    private modalService: BsModalService,
    private location: Location
  ) { }

  ngOnInit() {
    this.route.params
      .pipe(switchMap((params: Params) => this.service.getMaterial(params['id'])))
      .subscribe(material => { this.material = material; });
    }

  onSubmit() {
    this.service.updateMaterial(this.material)
      .subscribe(material => {
        this.material = material; //this.materialcopy = material;
      })
  }

  voltar(): void{
    this.location.back();
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template); // {3}
  }

}
