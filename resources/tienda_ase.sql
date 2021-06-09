-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-06-2021 a las 20:11:53
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda_ase`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `cliente_id` int(11) NOT NULL,
  `nombre` int(11) NOT NULL,
  `email` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_promociones`
--

CREATE TABLE `cliente_promociones` (
  `cliente_id` int(11) NOT NULL,
  `promocion_id` int(11) NOT NULL,
  `tipo_promocion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras_clientes`
--

CREATE TABLE `compras_clientes` (
  `cliente_id` int(11) NOT NULL,
  `articulo_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha_compra` date NOT NULL,
  `costo_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historico_compras_proveedor`
--

CREATE TABLE `historico_compras_proveedor` (
  `usuario_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `proveedor_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha_compra` date NOT NULL,
  `costo_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `articulo_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `costo_unitario` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `permiso_id` int(11) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `producto_id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` text NOT NULL,
  `costo_unitario` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_proveedor`
--

CREATE TABLE `productos_proveedor` (
  `proveedor_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones_t1`
--

CREATE TABLE `promociones_t1` (
  `promocion_id` int(11) NOT NULL,
  `articulo_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `descuento` double NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_final` date NOT NULL,
  `es_promocion_general` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones_t2`
--

CREATE TABLE `promociones_t2` (
  `promocion_id` int(11) NOT NULL,
  `articulo_regalo_id` int(11) NOT NULL,
  `fecha_ini` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `es_promocion_general` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones_t3`
--

CREATE TABLE `promociones_t3` (
  `promocion_id` int(11) NOT NULL,
  `descuento` double NOT NULL,
  `fecha_ini` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `es_promocion_general` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promocion_t2_articulos`
--

CREATE TABLE `promocion_t2_articulos` (
  `promocion_id` int(11) NOT NULL,
  `articulo_compra_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promocion_t3_articulos`
--

CREATE TABLE `promocion_t3_articulos` (
  `promocion_id` int(11) NOT NULL,
  `articulo_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promocion_t4`
--

CREATE TABLE `promocion_t4` (
  `promocion_id` int(11) NOT NULL,
  `descuento` double NOT NULL,
  `fecha_ini` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `es_promocion_general` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `proveedor_id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `direccion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usuario_id` int(11) NOT NULL,
  `permiso_id` int(11) NOT NULL,
  `usuario` varchar(15) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `contrasenia` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cliente_id`);

--
-- Indices de la tabla `cliente_promociones`
--
ALTER TABLE `cliente_promociones`
  ADD KEY `fk_cliente_promociones` (`cliente_id`);

--
-- Indices de la tabla `compras_clientes`
--
ALTER TABLE `compras_clientes`
  ADD KEY `fk_compras_cliente` (`cliente_id`),
  ADD KEY `fk_compras_articulo` (`articulo_id`);

--
-- Indices de la tabla `historico_compras_proveedor`
--
ALTER TABLE `historico_compras_proveedor`
  ADD KEY `fk_usuario_historico` (`usuario_id`),
  ADD KEY `fk_proveedor_historico` (`proveedor_id`),
  ADD KEY `fk_producto_historico` (`producto_id`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`articulo_id`),
  ADD KEY `fk_producto_id` (`producto_id`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`permiso_id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`producto_id`);

--
-- Indices de la tabla `productos_proveedor`
--
ALTER TABLE `productos_proveedor`
  ADD KEY `fk_producto_proveedor` (`producto_id`),
  ADD KEY `fk_proveedor_id` (`proveedor_id`);

--
-- Indices de la tabla `promociones_t1`
--
ALTER TABLE `promociones_t1`
  ADD PRIMARY KEY (`promocion_id`),
  ADD KEY `fk_promociont1_articulo` (`articulo_id`);

--
-- Indices de la tabla `promociones_t2`
--
ALTER TABLE `promociones_t2`
  ADD PRIMARY KEY (`promocion_id`),
  ADD KEY `fk_promocion2_articulo` (`articulo_regalo_id`);

--
-- Indices de la tabla `promociones_t3`
--
ALTER TABLE `promociones_t3`
  ADD PRIMARY KEY (`promocion_id`);

--
-- Indices de la tabla `promocion_t2_articulos`
--
ALTER TABLE `promocion_t2_articulos`
  ADD KEY `fk_promocion2_articulo_compra` (`articulo_compra_id`),
  ADD KEY `fk_promocion2_promo2` (`promocion_id`);

--
-- Indices de la tabla `promocion_t3_articulos`
--
ALTER TABLE `promocion_t3_articulos`
  ADD KEY `fk_promociont3_articulo` (`articulo_id`),
  ADD KEY `fk_promociont3_promo_id` (`promocion_id`);

--
-- Indices de la tabla `promocion_t4`
--
ALTER TABLE `promocion_t4`
  ADD PRIMARY KEY (`promocion_id`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`proveedor_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario_id`),
  ADD KEY `fk_permiso_id_tb` (`permiso_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cliente_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `articulo_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `permiso_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `producto_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `promociones_t1`
--
ALTER TABLE `promociones_t1`
  MODIFY `promocion_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `promociones_t2`
--
ALTER TABLE `promociones_t2`
  MODIFY `promocion_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `promociones_t3`
--
ALTER TABLE `promociones_t3`
  MODIFY `promocion_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `promocion_t4`
--
ALTER TABLE `promocion_t4`
  MODIFY `promocion_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `proveedor_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente_promociones`
--
ALTER TABLE `cliente_promociones`
  ADD CONSTRAINT `fk_cliente_promociones` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`);

--
-- Filtros para la tabla `compras_clientes`
--
ALTER TABLE `compras_clientes`
  ADD CONSTRAINT `fk_compras_articulo` FOREIGN KEY (`articulo_id`) REFERENCES `inventario` (`articulo_id`),
  ADD CONSTRAINT `fk_compras_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`);

--
-- Filtros para la tabla `historico_compras_proveedor`
--
ALTER TABLE `historico_compras_proveedor`
  ADD CONSTRAINT `fk_producto_historico` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`producto_id`),
  ADD CONSTRAINT `fk_proveedor_historico` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedores` (`proveedor_id`),
  ADD CONSTRAINT `fk_usuario_historico` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`usuario_id`);

--
-- Filtros para la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `fk_producto_id` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`producto_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos_proveedor`
--
ALTER TABLE `productos_proveedor`
  ADD CONSTRAINT `fk_producto_proveedor` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`producto_id`),
  ADD CONSTRAINT `fk_proveedor_id` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedores` (`proveedor_id`);

--
-- Filtros para la tabla `promociones_t1`
--
ALTER TABLE `promociones_t1`
  ADD CONSTRAINT `fk_promociont1_articulo` FOREIGN KEY (`articulo_id`) REFERENCES `inventario` (`articulo_id`);

--
-- Filtros para la tabla `promociones_t2`
--
ALTER TABLE `promociones_t2`
  ADD CONSTRAINT `fk_promocion2_articulo` FOREIGN KEY (`articulo_regalo_id`) REFERENCES `inventario` (`articulo_id`);

--
-- Filtros para la tabla `promocion_t2_articulos`
--
ALTER TABLE `promocion_t2_articulos`
  ADD CONSTRAINT `fk_promocion2_articulo_compra` FOREIGN KEY (`articulo_compra_id`) REFERENCES `inventario` (`articulo_id`),
  ADD CONSTRAINT `fk_promocion2_promo2` FOREIGN KEY (`promocion_id`) REFERENCES `promociones_t2` (`promocion_id`);

--
-- Filtros para la tabla `promocion_t3_articulos`
--
ALTER TABLE `promocion_t3_articulos`
  ADD CONSTRAINT `fk_promociont3_articulo` FOREIGN KEY (`articulo_id`) REFERENCES `inventario` (`articulo_id`),
  ADD CONSTRAINT `fk_promociont3_promo_id` FOREIGN KEY (`promocion_id`) REFERENCES `promociones_t3` (`promocion_id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_permiso_id_tb` FOREIGN KEY (`permiso_id`) REFERENCES `permisos` (`permiso_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
