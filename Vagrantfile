# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  unless Vagrant.has_plugin?('vagrant-notify-forwarder')
    puts "Run `vagrant plugin install vagrant-notify-forwarder` to continue setup"
    exit 1
  end

  config.vm.box = "ubuntu/trusty64"

  config.vm.network "private_network", ip: "192.168.50.1"

  config.vm.provider "virtualbox" do |v|
    v.memory = 5120
    v.cpus = 2
  end

  if Vagrant.has_plugin?('vagrant-cachier')
    config.cache.scope = :box
  else
    puts "Run `vagrant plugin install vagrant-cachier` to reduce caffeine intake when provisioning"
  end
end
