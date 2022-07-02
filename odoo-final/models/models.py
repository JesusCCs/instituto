# -*- coding: utf-8 -*-

from odoo import models, fields, api


class Nave(models.Model):
    _name = 'space_travel.nave'

    name = fields.Char(string="Categoria", required=True, help="El nombre de la categoría")
    modelo = fields.Char(string="Modelo", required=True, help="El modelo de la naves")
    imagen = fields.Image(string="Fotografía", help="Una imagen de la nave")
    num_pasajeros = fields.Integer(string="Nº pasajeros",
                                   required=True, help="La cantidad límite de pasajeros que puede llevar")
    tipo_combustible = fields.Selection(
        selection=[('h', 'Hidrógeno'), ('fosil', 'Combustible fósil'), ('solar', 'Vela solar')],
        default="h",
        string="Combustible",
        required=True, help="La cantidad límite de pasajeros que puede llevar")
    descripcion = fields.Text(string="Descripción", help="Breve descripción de la nave")


class Planeta(models.Model):
    _name = 'space_travel.planeta'

    name = fields.Char(string="Identificación", required=True, help="Identificador del planeta")
    nombre = fields.Char(string="Nombre", required=True, help="El nombre del planeta")
    descripcion = fields.Text(string="Descripción", help="La descripción del planeta")
    colonizado = fields.Boolean(string="Colonizado", required=True)
    imagen = fields.Image(string="Fotografía", help="Una imagen del planeta vista desde el espacio")


class Ruta(models.Model):
    _name = 'space_travel.ruta'

    name = fields.Char(string="Ruta", required=True, help="Código identificativo de la ruta espacial")
    nave = fields.Many2one("space_travel.nave", string="Nave", required=True)
    origen = fields.Many2one("space_travel.planeta", string="Origen", required=True)
    destino = fields.Many2one("space_travel.planeta", string="Destino", required=True)

    currency_id = fields.Many2one('res.currency', string='Moneda', required=True)
    precio = fields.Monetary(string="Precio", required=True)


class Viaje(models.Model):
    _name = 'space_travel.viaje'

    name = fields.Char(string="Título Viaje", required=True, help="El identificador del viaje")
    fecha_partida = fields.Date(string="Fecha de partida", required=True)
    fecha_llegada = fields.Date(string="Fecha de destino", required=True)
    ruta = fields.Many2one("space_travel.ruta", string="Ruta", required=True)
    viajero = fields.Many2one("res.partner", string="Viajero", required=True)
