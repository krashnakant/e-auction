/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EauctionTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SaleDetailComponent } from '../../../../../../main/webapp/app/entities/sale/sale-detail.component';
import { SaleService } from '../../../../../../main/webapp/app/entities/sale/sale.service';
import { Sale } from '../../../../../../main/webapp/app/entities/sale/sale.model';

describe('Component Tests', () => {

    describe('Sale Management Detail Component', () => {
        let comp: SaleDetailComponent;
        let fixture: ComponentFixture<SaleDetailComponent>;
        let service: SaleService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EauctionTestModule],
                declarations: [SaleDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SaleService,
                    JhiEventManager
                ]
            }).overrideTemplate(SaleDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SaleDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SaleService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Sale(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.sale).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
