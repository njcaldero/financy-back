package com.financy.service;

import com.financy.entity.Cuenta;


import java.util.List;


public interface ICuentaService  {

    public List<Cuenta> GetAll();
    public Cuenta Get(long id);
    public Cuenta Create(Cuenta cuenta) throws Exception;
}
