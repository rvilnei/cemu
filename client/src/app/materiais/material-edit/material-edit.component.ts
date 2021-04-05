import { MateriaisService } from './../materiais.service';
import { switchMap } from 'rxjs/operators';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-material-edit',
  templateUrl: './material-edit.component.html',
  styleUrls: ['./material-edit.component.css']
})
export class MaterialEditComponent implements OnInit {
  inscricao: Subscription
  material: any = {};
  private paramId: Params;
  materialTipo$: Observable<any[]>; // para usar | async
  materialStatu$: Observable<any[]>;


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: MateriaisService
  ) { }

  ngOnInit() {
    this.materialTipo$ = this.service.getTipos();
    this.materialStatu$ = this.service.getStatus();
    this.inscricao = this.route.params
      .pipe(switchMap( (params: Params) => this.service.getMaterial(params['id'])))
      .subscribe(material => { this.material = material; });  
  }

  ngOnDestroy(){
    this.inscricao.unsubscribe();
  }

  onEdit( material ){
    this.material = material
  }

  newMaterial(){
    this.router.navigate([ '/materialNovo'] );
  }

  save(): void {
    if ( this.material.id ) {
    this.service.updateMaterial(this.material)
      .subscribe(material => {
        this.material = material; //this.materialcopy = material;
        this.router.navigate([ '/materiais'] );
      })
    } else {
      console.log('**********');
      this.service.addMaterial(this.material)
      .subscribe(material => {
          this.material = material; //this.materialcopy = material;
          this.router.navigate([ '/materiais'] );
    })
  }
}

}
