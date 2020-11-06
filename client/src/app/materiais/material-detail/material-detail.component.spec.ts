import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialDetalheComponent } from './material-detalhe.component';

describe('MaterialDetalheComponent', () => {
  let component: MaterialDetalheComponent;
  let fixture: ComponentFixture<MaterialDetalheComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterialDetalheComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialDetalheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
