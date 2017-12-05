import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Sale } from './sale.model';
import { SaleService } from './sale.service';

@Injectable()
export class SalePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private saleService: SaleService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.saleService.find(id).subscribe((sale) => {
                    if (sale.start) {
                        sale.start = {
                            year: sale.start.getFullYear(),
                            month: sale.start.getMonth() + 1,
                            day: sale.start.getDate()
                        };
                    }
                    if (sale.end) {
                        sale.end = {
                            year: sale.end.getFullYear(),
                            month: sale.end.getMonth() + 1,
                            day: sale.end.getDate()
                        };
                    }
                    this.ngbModalRef = this.saleModalRef(component, sale);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.saleModalRef(component, new Sale());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    saleModalRef(component: Component, sale: Sale): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.sale = sale;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
